clear
clc
close all

% 1) 
angle = 0:60:360; % nebo 0:pi/3:2*pi a pouzit obyc sin/cos
x = cosd(angle);
y = sind(angle);
plot(x,y)
axis square

% 2) Funkce na konci file
% 3)
x = -5:.05:5;

% a)
figure('Name', 'Průběhy funkce f(x,y) v jenom grafu')
plot(x, f(x), x, f(x, 1), x , f(x, 2))
title('Graf funkce f(x,y)')
legend('f(x)','f(x,1)', 'f(x,2)', 'Location', 'best')
xlabel('x')
ylabel('y')

% b) Velikost souboru (PDF < JPG < BMP), kvalita (podle me) nejlepsi v PDF
figure('Name', 'Průběhy funkce f(x,y) s použitím subplot()')

ax1 = subplot(3, 1, 1);
plot(x, f(x), 'Color', 'r')
title('Graf funkce f(x)')
legend('y = xe^{-x^2} + tanh(0)', 'Location', 'southeast')
xlabel('x')
ylabel('y')

ax2 = subplot(3, 1, 2);
plot(x, f(x, 1), 'Color', 'g')
title('Graf funkce f(x,1)')
legend('y = xe^{-x^2 - 1} + tanh(1)', 'Location', 'southeast')
xlabel('x')
ylabel('y')

ax3 = subplot(3, 1, 3);
plot(x, f(x, 2), 'Color', 'b')
title('Graf funkce f(x,2)')
legend('y = xe^{-x^2 - 4} + tanh(2)', 'Location', 'southeast')
xlabel('x')
ylabel('y')

linkaxes([ax1, ax2, ax3], 'x')

% 4)
x = -5:.1:5;
y = -6:.2:6;
y(length(x)) = 0;

figure
[x, y] = meshgrid(-5:0.1:5 ,-6:0.2:6);
z = f(x,y);
mesh(x,y,z)
xlabel('x')
ylabel('y')
zlabel('z')

function z = f(x, y)
    if nargin < 2 
        y = 0; end
    z = x.*exp(1).^(-x.^2-y.^2) + tanh(x.*y);
end
