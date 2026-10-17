% (c) pzdrs bohac, marsel horvad
clear all; clc; close all; echo off

[data, fs] = audioread('ovcaci-housle.wav');

samples = 256;
overlap = 128;
N = numel(data);

% vykreslení původního audia ovcaci
figure;
subplot(3,1,1);
plot((0:N-1) / fs, data);

% zcr vykreslení a funkce
frames = buffer(data,samples,overlap);
[row, columns] = size(frames);

for n = 1:columns
    % pocet pruchodu nulou
    zcr(n) = (1/(2*(N-1)))*sum(abs(diff(sign(frames(:,n)))));
end

% vykresli zero crossing rate analyzu signalu
subplot(3,1,2);
plot(zcr);

subplot(3,1,3);
spectrogram( ...
    data, ...
    hamming(samples), ...
    overlap, ...
    samples, ...
    fs, ...
    'yaxis' ...
);