from collections import defaultdict
from itertools import islice


def factorize(number: int) -> list:
    factors = []
    d = 2
    while number > 1:
        while number % d == 0:
            # dokud bezezbytku
            factors.append(d)
            number /= d
        # pak zkousim dalsi vetsi cisla
        d = d + 1
        if d * d > number:
            if number > 1:
                factors.append(number)
            break
    return factors


def queen(m: int, n: int, x: int, y: int):
    for i in range(n):
        for j in range(m):
            if i == x and j == y:
                print('Q', end=' ')
            elif i == x or j == y or abs(i - x) == abs(j - y):
                print('x', end=' ')
            else:
                print('.', end=' ')
        print()


def censor_number(limit: int, target: int):
    for i in range(1, limit + 1):
        print('*' if i == target else i)


def text_analysis(file_path: str) -> tuple[dict, dict]:
    letters = defaultdict(int)
    words = defaultdict(int)
    with open(file_path, 'r', encoding='utf-8') as f:
        for line in f:
            for word in line.split():
                words[word] += 1
                for letter in word:
                    letters[letter] += 1

    return dict(letters), dict(words)


def get_words(word_count: int, min_word_len: int, analysis: tuple[dict, dict]) -> dict:
    return dict(islice(
        {
            k: v for k, v in sorted(analysis[1].items(), key=lambda item: item[1], reverse=True)
            if len(k) >= min_word_len
        }.items(), word_count)
    )


def cypher(file_path: str, output_path: str, key: int = 5, decipher: bool = False):
    with open(file_path, 'r', encoding='utf-8') as input_file:
        with open(output_path, 'w', encoding='utf-8') as output_file:
            for line in input_file:
                for letter in line:
                    output_file.write(chr(ord(letter) + key * (-1 if decipher else 1)))


if __name__ == '__main__':
    cypher('input.txt', 'output.txt')
    cypher('output.txt', 'output1.txt', decipher=True)
