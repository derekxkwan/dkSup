//sample chopper, loc = location between 0 and 1

DxkNobuM {
	* ar {|buf, loc = -1,t_trig|
	var out,rdphs,bufsize,trig,del_trig,real_loc,env,gate,ramp= 0.01;
	trig = HPZ1.kr(loc);
	trig = (trig < 0) + (trig > 0) +t_trig;
	del_trig = TDelay.kr(trig, ramp);
	gate = SetResetFF.kr(del_trig, trig);
	bufsize = BufFrames.ir(buf);
	real_loc = Clip.kr(loc*bufsize, 0, bufsize);
	rdphs = Phasor.ar(del_trig, BufRateScale.kr(buf), 0, bufsize, real_loc);
	env = EnvGen.ar(Env.asr(ramp,1,ramp),gate);
	out = BufRd.ar(1, buf, rdphs)*env;
	^out
	}
}

DxkNobuS {
	* ar {|buf, loc = -1,t_trig|
	var out,rdphs,bufsize,trig,del_trig,real_loc,env,gate,ramp= 0.01;
	trig = HPZ1.kr(loc);
	trig = (trig < 0) + (trig > 0) +t_trig;
	del_trig = TDelay.kr(trig, ramp);
	gate = SetResetFF.kr(del_trig, trig);
	bufsize = BufFrames.ir(buf);
	real_loc = Clip.kr(loc*bufsize, 0, bufsize);
	rdphs = Phasor.ar(del_trig, BufRateScale.kr(buf), 0, bufsize, real_loc);
	env = EnvGen.ar(Env.asr(ramp,1,ramp),gate);
	out = BufRd.ar(2, buf, rdphs)*env;
	^out
	}
}





