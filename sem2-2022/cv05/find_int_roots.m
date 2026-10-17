function [x, M] = find_int_roots(p)
    % Funkce najde celočíselné kořeny polynomu za pomocí Hornerova schématu

    % konstantni clen
    konst = abs(p(find(p,1,'last')));
    
    % range 1 az konst
    delitele = 1:konst;
    % vsechny possible delitele konstantniho clenu
    delitele = delitele(rem(konst,delitele)==0);
    
    % Vyfiltrování dělitelů tak, aby splňovali větu o omezenosti kořenů
    if (length(p) > 1)
        A = max(abs(p(2:end)));
        an = p(1);
        potencialniKoreny = delitele(abs(delitele) < 1 + A./abs(an));
    else
        potencialniKoreny = delitele;
    end
    
    % +- verze potencialnich korenu
    potencialniKoreny = [potencialniKoreny -1 * potencialniKoreny];
    potencialniKoreny(end + 1) = 0;

    % Inicializace Hornerova schématu
    M = p;
    x = [];
    
    %Když to je jenom konstanta, tak nemá smysl tady dělat fuckin schéma
    if (length(p) == 1)
         return;
    end

    posledniPolynom = p;
    
    % Dokud máme polynomy na dělení, budeme hledat kořeny
    while ~isempty(posledniPolynom)
        % Interní proměnná
        % Matlab je totiž tak retardovanej, že nemá labely pro smyčky
        % Takže musím jak neandrtálec smyčky ovládat skrz proměnné
        flag = 0;

        % pro kazdej potencialni koren
        for i=1:numel(potencialniKoreny)
            % Dělení polynomů
            [q, r] = deconv(posledniPolynom, [1 potencialniKoreny(i)]);
            
            % pokud je zbytek po deleni != 0 cislo neni koren takze
            % continue a zkusit dalsi delitel
            if (any(r ~= 0))
                continue
            end
            % zbytek po deleni je 0 => nasli jsme koren
            % vydelenej polynom pouziju v dalsi iteraci
            posledniPolynom = q;

            % do vektoru korenu appendnu tenhle koren
            x(end + 1) = potencialniKoreny(i);
            % hodim to do matice postupu uplne dolu
            M(end + 1, 1:length(q)) = q;

            % konec computations
            flag = 1;
            break;
        end
        
        if (flag == 1) continue
        else break; end
    end
    
    % Čísla tam mám poukládaná s opačnýma znamínkama
    x = -1 * x;
    
    % prevod na sloupcovej vektor pokud je potreba
    if (isrow(x))
        x = x';
    end
end