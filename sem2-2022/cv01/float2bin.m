function b = float2bin(number,dec_length)
    % celociselna cast
    whole = floor(number);
    % cast za desetinnou carkou
    decimal = number - whole;
    nodecimal = false;
    if(decimal == 0)
        nodecimal = true;
    end

    % prevod celociselne casti na binarku - na konci se to flipne
    k = 1;
    wholeBin = 0;
    while(whole > 0)
        wholeBin(k) = mod(whole,2);
        whole = floor((whole-wholeBin(k))/2);
        k = k + 1;
    end

    k = 1;
    decimalBin = 0;
    % To convert the fractional part to binary, multiply fractional part with 2 and take the one bit which appears before the decimal point.
    % Follow the same procedure with after the decimal point (.) part until it becomes 1.0.
    % ja ale limituju dylku takze ten == 1.0 check ignoruju
    while(dec_length > 0)
        if(decimal == 0); break; end
        integer = floor(decimal*2);
        decimalBin(k) = integer;
        decimal = (decimal*2)-integer;
        dec_length = dec_length - 1;
        k = k + 1;
    end
    % smazat mezery, dat dohromady celociselnou cast a decimalni cast
    % odeleno teckou
    s = erase(strcat(reverse(num2str(wholeBin)), ".", num2str(decimalBin)), " ");
    % uprava formatovani pokud puvodni desitkove cislo bylo < 0 => i.e.
    % 0.75
    if(nodecimal == true)
        s = sprintf('%.f', s);
    end
    b = char(s);
end
