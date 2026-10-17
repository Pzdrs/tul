clear all;
close all;
T = 1;
Fs = 1000;
t=0:1/Fs:T-1/Fs;

% Vytvoření signálu x o složkách 100, 200, 300 a 400 Hz
x=cos(2*pi*100*t)+cos(2*pi*200*t)+cos(2*pi*300*t)+cos(2*pi*400*t);

N = 1000;
F = 0:Fs/N:Fs-Fs/N;

X = fft(x,N);

subplot(2, 1 ,1)
stem(F,abs(X)/(N));
title('Spektrum signálu x');
xlabel('f [Hz]');
ylabel('|A|');

% Koeficienty nulovacího filtru pro frekvenci 300 Hz
Fn=300/Fs;
B = [1 -2*cos(2*pi*Fn) 1];
A = [1];

y = filter(B,A,x);
Y = fft(y,N);
subplot(2, 1 ,2)
stem(F,abs(Y)/(N));
title('Spektrum signálu y');
xlabel('f [Hz]');
ylabel('|A|');


figure;
zplane(B,A);
figure;
freqz(B,A);





