% (c) petr bohac, marsel horvad
% Success rate for the full data set: 42.4%
% Pocet vypoctu vzdalenosti pro cely data set: N*M -> 9_000_000
% Trvalo to celkem dlouho, cely to jelo na jednom vlakne, multithreading by
% to urychlil for suuuure
% Pro vypocet jedne vzdalenosti je potreba udelat 1024 rozdilu pro dva
% obrazky o rozmerech 32x32 pixelu

load('digits_tren.mat');load('digits_test.mat');
N = 1000; M=9000; pocet_pokusu=0; pocet_spravnych= 0;

for i = 1:N
    x = test_data(i, :, :);
    x_trida = test_trida(i);
    if mod(i, 10) == 0
        disp(['Index: ' num2str(i)])
        disp(['Success rate so far: ' num2str(pocet_spravnych/pocet_pokusu * 100) '%'])
    end
    for j = 1:M
        v = tren_data(j, :, :);
        v_trida = tren_trida(j);
        tridy_vzoru(j) = v_trida;
        dist(j) = sum(sum(abs(x-v)));
    end
    [min_dist, index] = min(dist);
    nejblizsi_trida = tridy_vzoru(index);
    if x_trida == nejblizsi_trida
        pocet_spravnych = pocet_spravnych + 1;
    end
    pocet_pokusu = pocet_pokusu + 1;
end
uspesnost = pocet_spravnych/pocet_pokusu * 100;
disp(['Final success rate: ', num2str(uspesnost), '%'])