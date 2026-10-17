clear all;
% data 
test_soubor = 'c1_p0001_v02.jpg';
tren_soubor = ['c5_p0001_v02.jpg';'c6_p0001_v02.jpg';'c7_p0001_v02.jpg';'c8_p0001_v02.jpg';'c9_p0001_v02.jpg';
               'c0_p0001_v02.jpg';'c1_p0001_v02.jpg';'c2_p0001_v02.jpg';'c3_p0001_v02.jpg';'c4_p0001_v02.jpg'];
tren_map = [5 6 7 8 9 0 1 2 3 4];
               
% porovnani vzdalenosti
x = imread(['data/' test_soubor]);
for j = 1:10
    v = imread(['data/' tren_soubor(j, :)]);
    if isequal(v, x)        
        dist(j) = Inf;
    else
        dist(j) = sum(sum(abs(x - v)));
    end
end

% vyhodnoceni
[min_dist, index] = min(dist);
disp (['Obrázek rozpoznán jako ', num2str(tren_map(index))])
image(imread(['data/' tren_soubor(index,:)]))