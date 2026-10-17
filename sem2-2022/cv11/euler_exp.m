function [t,y] = euler_exp(f,a,b,y0,h)
    % dam initial hodnoty do output vektoru aby byly dostupny v loopu
    y(1) = y0;
    t(1) = a;
    % pocet iteraci
    n = (b-a)/h;
    for i=1:n
        % Yn = Yn-1 + hF(xn-1, yn-1)
        y(i+1) = y(i) + h * f(t(i), y(i));
        % cas tohoto kroku
        t(i+1) = t(i) + h;
    end
end

