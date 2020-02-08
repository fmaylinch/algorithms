

def factorial(n: int) -> int:
    if n == 1:
        return 1
    return n * factorial(n-1)


def factorial_iter(n: int) -> int:

    result = 1
    while n > 1:
        result *= n
        n -= 1
    return result


print(factorial(4))
print(factorial_iter(4))
