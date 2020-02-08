from typing import List, Dict, Tuple, Set

Numbers = List[int]  # type alias


def product_except_self(array: Numbers) -> Numbers:

    result = [1] * len(array)

    for i in range(len(array) - 1):
        result[i+1] = result[i] * array[i]

    current = 1
    for i in range(len(array) - 1, 0, -1):
        current *= array[i]
        result[i-1] *= current

    return result


a = [5, 2, 3, 4]
print(a)
print(product_except_self(a))
