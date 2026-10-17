clc;
clear all;
close all;

%(c) Pzdrs bohac, Marsel Horvad

f=2;
Fs=1000;
slozky_count=1000;
t=0:(1/Fs):1;

% initial
X1=zeros(size(t));
X2=X1;

% prvni vzoreckos
for n=1:2:slozky_count
    X1=X1+cos(2*pi*n*f.*t-(pi/2))./n;
end

% obdelnikovy signal
subplot(2,2,1);
plot(t,X1);
title('obdélníkový signál');
xlabel('t[sec]');

N=1000;
f=0:Fs/N:Fs/2-Fs/N;
X=fft(X1,N);

subplot(2,2,2);
stem(f(1:40),1/(N/2)*abs(X(1:40)),'.');
xlabel('f[Hz]');

f=2;

% druhy vzoreckos
for n=1:slozky_count
    X2=X2+cos(2*pi*n*f.*t-(pi/2))./n;
end

% pilovy signal
subplot(2,2,3);
plot(t,X2);
title('pilový signál');
xlabel('t[sec]');

f=0:Fs/N:Fs/2-Fs/N;
X=fft(X2,N);

subplot(2,2,4);
stem(f(1:40),1/(N/2)*abs(X(1:40)),'.');
xlabel('f[Hz]');