import collections as col
from random import randint


print("\n--- Default values, arguments by name ---")

def my_add(a, b=1, c=0):
    return a + b + c

print("1+2+0 =", my_add(1, 2))
print("5+1+0 =", my_add(5))
print("5+1+2 =", my_add(5, c=2))


print("\n--- Lists ---")
emptyList1 = list()
emptyList2 = []  # literal for list()
nums = [1, 2, 3, 4]
nums.append(5)
nums.insert(1, 7)
print(nums)
x = nums.pop(1)
print(nums, f"popped index 1: {x}")
x = nums.pop()
print(nums, f"popped last value: {x}")
print("Last value:", nums[-1])
print("In ranges, the last index is not included:")
print("nums where 1 <= index < 3 :", nums[1:3])
print("nums where 1 <= index :", nums[1:])
print("nums until index < len(nums)-1 :", nums[:-1])


print("\n--- Queues (collections.deque) ---")
deque = col.deque()
deque.append(1)
deque.append(2)
deque.append(3)
print(deque)
x = deque.popleft()
print(deque, "popped:", x)


print("\n--- for loops ---")
print("\nfor items:", end=" ")
for n in nums:
    print(n, end=" ")
print("\nfor indexes:", end=" ")
for i in range(len(nums)):
    print(nums[i], end=" ")
print("\nfor indexes reversed:", end=" ")
for i in reversed(range(len(nums))):
    print(nums[i], end=" ")
print("\nfor indexes backwards:", end=" ")
for i in range(len(nums)-1, -1, -1):
    print(nums[i], end=" ")
print("\nfor enumerate:", end=" ")
for i, x in enumerate(nums):
    print(f'nums[{i}] = {x}', end=" ")
print("\n")

print("\n--- filter and map ---")

# map and filter
odds = filter(lambda x: x % 2 == 1, nums)
nums1 = map(lambda x: x + 1, nums)
print("filter(odd):", list(odds))  # list is needed to display it
print("map(+1):", list(nums1))     # list is needed to display it

# map and filter using comprehensions
odds = [x for x in nums if x % 2 == 1]
nums1 = [x+1 for x in nums]
print("filter(odd):", odds)
print("map(+1):", nums1)


print("\n--- zip ---")
zipped = zip([1, 2, 3], ["one", "two", "three"])
for n, w in zipped:
    print(f'{n} -> {w}')


print("\n--- Sets ---")

# Set
s = set()  # there's no set literal
s.add(1)
s.add(2)
s.add(1)
print(s)


print("\n--- Classes ---")


class Box:  # Box(object) in Python 2

    def __init__(self, x):
        self.x = x
        Box.count += 1

    def add(self, x):
        self.x += x

    @staticmethod
    def count():
        return Box.count

    def __str__(self):
        return "Box with " + str(self.x)

    def __repr__(self):
        return "Box(" + str(self.x) + ")"


Box.count = 0


box = Box(2)
print("box:", box)  # using __str__
print("box:", repr(box))  # using __repr__
print("box.x:", box.x)
box1 = Box(0)
box2 = Box(0)
print("Box.count:", Box.count)


print("\n--- random ---")
randoms = [randint(1, 5) for _ in range(10)]
print("randoms:", randoms)


print("\n--- sorting ---")
lists = [[1, 4], [4, 8], [2, 4], [2, 3]]
lists.sort()
print(lists)
lists.sort(key=lambda pair: (pair[1], pair[0]))
print(lists)


print("\n--- strings ---")
s = "what"
for c in s:
    print(c, end=" ")
print()
for i in range(len(s)):
    print(s[i], end=" ")
print()

chars = [c for c in "hey"]
print(chars)
print("".join(chars))
print("".join(["one", "two", "three"]))

print("\n--- enumerate ---")
print(list(enumerate(range(5))))
print(min((v, i) for i, v in enumerate([4, 3, 8, 9, 5])))


print("\n--- tuples and switching values ---")
x1 = 1
x2 = 2
x1, x2 = x2, x1
print(x1, x2)


print("\n--- Dictionaries ---")
d = dict()
d = {}  # literal for dict()
d = {'one':1, 'two':2}
d['three'] = 3
for key in d:  # like d.keys()
    print(key)
for value in d.values():
    print(value)
for k, v in d.items():
    print(f'{k} -> {v}')
for k, v in zip(d.keys(), d.values()):
    print(f'{k} -> {v}')

lengths = {w: len(w) for w in ['one', "two", "three"]}
print(lengths)
