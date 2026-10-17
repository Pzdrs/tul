function [I,k] = adapt_trapz(f,a,b,maxlevel,tol)
    % Funkce realizuje adaptivní lichoběžníkovou kvadraturu
    % f - aproximovaná funkce (function handle @)
    % a - Spodní hranice intervalu
    % b - Horní hranice intervalu
    % maxlevel - Maximální počet úrovní zjemnění
    % tol - Požadovaná přesnost
    % I - aproximovaná hodnota integrálu
    % k - počet potřebných úrovní zjemnění
    
    LastApprox = 0;
    for k = 0:maxlevel
        % delta x
        dx = (b - a) / 2^k;
        oblast = a:dx:b;
        oblast = arrayfun(@(x) f(x), oblast);
        % vsechno krome krajnich bodu vynasobit 2
        oblast(2:end - 1) = oblast(2:end-1) .* 2;
    
        % odhad plochy pod krivkou
        I = sum(oblast) * (dx/2);
        % pokud mame dostatecne odhadnuto, tak koncime
        if (abs(I - LastApprox) < tol)
            break;
        end
        LastApprox = I;
    end
end