close all;
clear all;

% (c) petr bohac, marsel horvath - skakal pes pres oves

% predifined shit
% ---------------------------
BPM = 120;
A4 = 440;
q = nthroot(2,12);
% sample rate
Fs = 8000;
% perioda
Ts = 1/Fs;

% noty in relation to A4
G4 = A4/q^2;
E4 = A4 /q^5;
F4 = A4 /q^4;
D4 = A4 /q^7;

T_ctvrt = 60/BPM;
pauza_ctvrt = 0;
% ---------------------------

t_ctvrt = 0:Ts:T_ctvrt-Ts;
t_pul = 0:Ts:T_ctvrt*2-Ts;

% jak zni ten ton
A4_ctvrt = cos(2*pi*A4*t_ctvrt);
G4_ctvrt = cos(2*pi*G4*t_ctvrt);
E4_ctvrt = cos(2*pi*E4*t_ctvrt);
F4_ctvrt = cos(2*pi*F4*t_ctvrt);
D4_ctvrt = cos(2*pi*D4*t_ctvrt);
G4_pul = cos(2*pi*G4*t_pul);
F4_pul = cos(2*pi*F4*t_pul);
E4_pul = cos(2*pi*E4*t_pul);

% jak je dlouho ticho more
pauza_ctvrt = pauza_ctvrt*t_ctvrt;

sound([G4_ctvrt ...
    G4_ctvrt ...
    E4_ctvrt ...
    pauza_ctvrt ...
    G4_ctvrt G4_ctvrt ...
    E4_ctvrt pauza_ctvrt ...
    G4_ctvrt G4_ctvrt ...
    A4_ctvrt G4_ctvrt G4_pul F4_pul F4_ctvrt F4_ctvrt D4_ctvrt pauza_ctvrt F4_ctvrt F4_ctvrt D4_ctvrt pauza_ctvrt F4_ctvrt F4_ctvrt G4_ctvrt F4_ctvrt F4_pul E4_pul],Fs)