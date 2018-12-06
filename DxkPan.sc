DxkStPan {
	* ar {
		arg in = 0, pan = 0, mul = 1;
		var iptchan = in.numChannels;
		
		//l -> l: 1 : 1 -> 0
		//l -> r: 0 : 0 -> 1
		//r -> r: 0 -> 1 : 1
		//r -> l: 1->0 : 0

		var panner = Clip.kr([ (1 - pan), pan, (1 + pan), (-1 * pan)], 0, 1);
	
		var out = if(iptchan <= 1,
				{Pan2.ar(in, pan)},
			{
				[Mix.ar(output * [panner[0], panner[3]]), Mix.ar(output * [panner[1], panner[2]])];
			}
			);
		
		out = out * mul;
		^out
	}
}
		
