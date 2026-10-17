#lang scheme

(define (f a b c)
  (/ (sqrt (+ (* a a) (* b b))) (sqrt (+ (* b b) (* c c)))))

(f 9 3 1)

(define (odKonce n list)
  (cond
    [(empty? list) 0]
    [(= (car list) n) (length list)]
    [else (odKonce n (cdr list))]))

(odKonce 3 '(5 8 3 9 2 3 1))

(define-struct obchod (nazev cenik))
(define-struct zbozi (nazev cena))

(define test (list
              (make-obchod "Billa"
                           (list (make-zbozi "Mouka hladka" 12)
                                 (make-zbozi "Mattoni" 15)
                                 (make-zbozi "Chleb horal" 27)))
              (make-obchod "Lidl"
                           (list (make-zbozi "Rohlik" 3)
                                 (make-zbozi "Mouka hladka" 10)))
              (make-obchod "Tesco"
                           (list (make-zbozi "Mattoni" 15)
                                 (make-zbozi "Chleb horal" 25)))
              ))

(define (prumernaCena jmeno obchody)
  (define (walkObchody obchody soucet pocet)
    (cond
      ; pokud jsme v zadnym obchodu zbozi nenasli vrat 0, jinak vrat ten average calculation
      [(empty? obchody) (if (= pocet 0) 0 (/ soucet pocet))]
      [else
       ; sekvencni let protoze
       (let* ((zbozi (obchod-cenik (car obchody)))
              ; najdeme si itemy s pozadovanym jmenem pres
              (matchingItems (filter (lambda (z) (string=? (zbozi-nazev z) jmeno)) zbozi)))
         ; zkontrolujeme jak dopadlo nase hledani 
         (if (empty? matchingItems)
             ; obchod zbozi nevede, skip na dalsi obchod
             (walkObchody (cdr obchody) soucet pocet)
             ; add to total a increment counter
             (walkObchody (cdr obchody)
                     (+ soucet (zbozi-cena (car matchingItems)))
                     (+ pocet 1))))]))

  (walkObchody obchody 0 0))


(prumernaCena "Mouka hladka" test)