% https://www.youtube.com/watch?v=PIPiv6gn_Ls
function [x,res,k] = newton(f,fp,x0,tol,maxit)
    k = 0;
    res = 0;
    lastX = x0;
    
    % respektuju maxit
    while k < maxit
        % vzorec pro vypocet pomoci newtonovy iteraci metody
        x = lastX - (f(lastX) / fp(lastX));
        % residuum - o kolik se lisi posledni iterace od nynejsi
        res = abs(lastX - x);

        % pokud mame dostatecne vypocitano, koncime
        if res < tol
            break;
        end
        lastX = x;
        k = k + 1;
    end
end