import string


def reverse_words_hack(text: str) -> str:
    return " ".join(reversed(text.split()))


def reverse_words(text: str) -> str:

    whitespace = set(string.whitespace)
    i = len(text) - 1
    words = []

    while i >= 0:

        while i >= 0 and text[i] in whitespace:
            i -= 1

        end = i

        while i >= 0 and text[i] not in whitespace:
            i -= 1

        word = text[i+1:end+1]
        words.append(word)

    return " ".join(words)


print(reverse_words("reverse these words!"))
