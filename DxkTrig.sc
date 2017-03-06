//detect changes in input
DxkChangeTrig {
	* kr {|in,t_trig|
	var trig;
	trig = HPZ1.kr(in);
	trig = (trig < 0) + (trig > 0) +t_trig;
	^trig
	}
}