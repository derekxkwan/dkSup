DxkMarkov1 {
	var <lastNote;
	var <dict;

	*new{
		^super.new.init();
	}

	init{
		dict = Dictionary.new;
		lastNote = nil;
	}

	getNext{
		var cur_last = lastNote;
		var last_exists = dict.atFail(cur_last.asString, nil).notNil;
		var next_poss; //next possibilities
		var next_note;
		while({not(last_exists);},
			{   var cur_keys = dict.keys;
				var rand_keyval = dict.at(cur_keys.keyAt(rand(cur_keys.size)));
				var rand_val = rand_keyval.at(rand(rand_keyval.size));
				cur_last = rand_val;
				last_exists = dict.atFail(cur_last.asString, nil).notNil;
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
		

	read{|note_array|
		note_array.do({|cur_note|
			if(lastNote != nil,
				{
					var cur_key = lastNote.asString;
					var key_exists = dict.atFail(cur_key, nil).notNil;
					if(not(key_exists),
						{dict.put(cur_key, [])}, {});
					dict.mergeItem(cur_key, [cur_note],
						{|new_val, old_val| new_val ++ old_val});
				}, {});
			lastNote = cur_note;
		});
	}
					

	readFromMidi{|cur_midi|
		// track, time. type, chan, note, vol?
		var cur_notes = cur_midi.midiEvents(
			{|val| val[2] == \noteOn}).collect({|val|
				val[4]});

		this.read(cur_notes);
		}

	readFromMidiPath{|midi_path|
		var cur_midi = SimpleMIDIFile.read(midi_path);
		this.readFromMidi(cur_midi);
	}
		


}