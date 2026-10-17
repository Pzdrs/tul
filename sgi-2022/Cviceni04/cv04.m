clc; clear all; close all;
% (c) petr bohac, marsel horvad
[sig, Fs] = audioread('cv04_00.wav');
% čas
t = 0:1/Fs:(length(sig)-1)/Fs;
subplot(3,1,1);
% Původní signál
plot(t,sig);
title('Původní signál');
xlabel('t[sec]');
ylabel('x[t]');

pocetVzorku = size(sig,1);
pocetCasti = ceil(pocetVzorku/100);
E = zeros(pocetCasti);

% ten vzorecek se sumou
for i = 1:1:pocetVzorku
    E(floor((i-1)/100)+1) = E(floor((i-1)/100)+1)+sig(i).^2;
end

subplot(3,1,2);
plot(E);
title('Krátkodobá energie');

D = zeros(pocetCasti-1);

% výpočet diference (druhej vzorecek)
for i=2:1:pocetCasti
    D(i-1) = E(i) - E(i-1);
end

subplot(3,1,3);
plot(D);
title('Zpětná diference energie')