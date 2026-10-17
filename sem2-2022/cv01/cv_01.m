% zmena vystupniho formatu kvuli nepresnosti v prevodu
format bank
% current time
mytime = datetime();
% prevod current casu na minuty
minutes = mytime.Hour*60+mytime.Minute+mytime.Second/60;
% prevod decimal na binary
float2bin(minutes, 10)