DxkMarkov1 {
	var <lastNote;
	var <dict;

	*new{
		^super.new.initM1();
	}

	initM1{

		dict = Dictionary.new;
		lastNote = nil;
	}

	getNext{
		var cur_last = lastNote;
		var last_exists = dict.atFail(cur_last.asString, nil).notNil;
		var next_poss; //next possibilities
		var next_note;
		while({last_exists == false},
			{   var cur_keys = dict.keys;
				var rand_key = cur_keys.at(rand(cur_keys.size));
				cur_last = rand_key.asFloat;
				last_exists = dict.at(rand_key).size > 0;
			});
		next_poss = dict.at(cur_last.asString);
		next_note = next_poss.at(rand(next_poss.size));
		lastNote = next_note;
		^next_note;
	}

	getNextFrom{|ipt_note|
		var ipt_exists = dict.atFail(ipt_note.asString, nil).notNil;
		if(ipt_exists == true,
			{lastNote = ipt_note}, {});
		^this.getNext();
	}

	read{|ipt_note|
		if(lastNote.notNil == true,
			{
				var cur_key = lastNote.asString;
				var key_exists = dict.atFail(cur_key, nil).notNil;
				if(key_exists == false,
					{dict.put(cur_key, [])}, {});
				dict.mergeItem(cur_key, [ipt_note],
					{|new_val, old_val| new_val ++ old_val});
			}, {});
		lastNote = ipt_note;
	}
		

	readArray{|note_array|
		lastNote = nil;
		note_array.do({|cur_note|
			this.read(cur_note);
		});
	}
					

	readFromMidi{|cur_midi|
		// track, time. type, chan, note, vol?
		var cur_notes = cur_midi.midiEvents(
			{|val| val[2] == \noteOn}).collect({|val|
				val[4]});

		this.readArray(cur_notes);
		}

	readFromMidiPath{|midi_path|
		var cur_midi = SimpleMIDIFile.read(midi_path);
		this.readFromMidi(cur_midi);
	}
		


}

DxkMarkov2 : DxkMarkov1 {
	
	var <lastNote2;


	//lastNote2 = last last note
	*new{
		^super.new.initM2();
	}

	initM2{

		lastNote2 = nil;
	}

	
	getNext{
		var cur_last2 = lastNote2;
		var cur_last = lastNote;
		var want_key = cur_last2.asString ++ "|" ++ cur_last.asString;
		var last_exists = dict.atFail(want_key, nil).notNil;
		var next_poss; //next possibilities
		var next_note;
		while({last_exists == false;},
			{   var cur_keys = dict.keys;
				var rand_key = cur_keys.at(rand(cur_keys.size));
				#cur_last2, cur_last = rand_key.split($|).collect({|x| x.asFloat});
				last_exists = dict.at(rand_key).size > 0;
			});
		next_poss = dict.at(want_key);
		next_note = next_poss.at(rand(next_poss.size));
		lastNote2 = cur_last;
		lastNote = next_note;
		^next_note;
	}

	getNextFrom{|ipt_note|
		var want_key = lastNote.asString ++ "|" ++ ipt_note.asString;
		var ipt_exists = dict.atFail(want_key, nil).notNil;
		if(ipt_exists == true,
			{
				lastNote2 = lastNote;
				lastNote = ipt_note;
			}, {});
		^this.getNext();
	}

	getNextFrom2{|ipt_note2, ipt_note|
		var want_key = ipt_note2.asString ++ "|" ++ ipt_note.asString;
		var ipt_exists = dict.atFail(want_key, nil).notNil;
		if(ipt_exists == true,
			{
				lastNote2 = ipt_note2;
				lastNote = ipt_note;
			}, {});
		^this.getNext();
	}

	read{|ipt_note|
		if(lastNote.notNil == true && lastNote2.notNil == true,
			{
				var cur_key = lastNote2.asString ++ "|" ++ lastNote.asString;
				var key_exists = dict.atFail(cur_key, nil).notNil;
				if(key_exists == false,
					{dict.put(cur_key, [])}, {});
				dict.mergeItem(cur_key, [ipt_note],
					{|new_val, old_val| new_val ++ old_val});
			}, {});
		lastNote2 = lastNote;
		lastNote = ipt_note;
	}
		
	readArray{|note_array|
		lastNote2 = nil;
		lastNote = nil;
		note_array.do({|cur_note|
			this.read(cur_note);
		});
	}

}