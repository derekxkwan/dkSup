DxkStPan {
	* ar {
		arg in = 0, pan = 0, mul = 1;
		var iptchan = in.numChannels;
		var clipped = pan.clip(-1.0,1.0);
		//l-l, l-r, r-r, r-l
		var panner = Select.ar(BinaryOpUGen('>=', clipped, 0),
			[
				K2A.ar([1, 0, clipped + 1, clipped * -1]),
				K2A.ar([(1 - clipped), clipped, 1, 0])
			]
			);
		var out = if(iptchan <= 1,
				{Pan2.ar(in, pan)},
			{[
					(in[0] * panner[0]) +
					(in[1] * panner[3]),
					(in[0] * panner[1]) +
					(in[1] * panner[2])
			]}
			);
		out = out * mul;
		^out
	}
}
		
