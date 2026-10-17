
% (c) pzdrs bohac, marsel horvad - the most premium code
clear; close all; clc
doba_trvani=2; % 2s
f1=4;
f2=2.5;
f3=12;
f4=7.25;
frequencies = [f1 f2 f3 f4];

Fs=10; % 10Hz
% 10Hz 2s - hertz je kolikrat za sekundu tudiz 10x za 1s, 20x za 2s
t=0:1/Fs:doba_trvani;

for n=1:numel(frequencies)
    frequency=frequencies(n);
    func=cos(2*pi*frequency*t);
    dft=DFT(func, 10);
    subplot(4,2,n)
    stem(abs(dft))
end

for n=1:numel(frequencies)
    frequency=frequencies(n);
    func=cos(2*pi*frequency*t);
    dft=DFT(func, 20);
    subplot(4,2,n+4)
    stem(abs(dft))
end

function X = DFT(x, N)
    for k=0:N-1
        sum=0;
        for n=0:N-1
            xn=x(n+1);
            sum = sum + xn*exp((-1i*2*pi*k*n)/N);
        end
        X(k+1)=(1/N)*sum;
    end
end