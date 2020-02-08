
class Node:
    def __init__(self, val, nxt=None):
        self.val = val
        self.nxt = nxt


def add_numbers(a, b):
    result = Node(0)
    current = result
    while a or b:
        if a:
            current.val += a.val
            a = a.nxt
        if b:
            current.val += b.val
            b = b.nxt
        nxt_val = current.val // 10
        current.val %= 10
        if a or b or nxt_val > 0:
            current.nxt = Node(nxt_val)
            current = current.nxt
    return result


def print_number(num):
    while num:
        print(num.val)
        num = num.nxt


# Numbers are in reverse order
num1 = Node(3, Node(6, Node(4)))       # ·463
num2 = Node(5, Node(7, Node(5)))       # ·575
print_number(add_numbers(num1, num2))  # 1038

