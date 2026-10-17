clear all
clc

%otevøe data uložená v souboru
x=importdata('P0716b.mat');
[par,io]=wblfit(x,0.05)