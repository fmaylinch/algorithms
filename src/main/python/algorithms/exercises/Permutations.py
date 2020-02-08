

# Writes permutations of other,
# and prints them together with prefix items
def permutations(prefix, other, result):

    if len(other) == 0:
        result.append(tuple(prefix))
        return

    for i in range(len(other)):
        first_other = other.pop(0)
        prefix.append(first_other)
        permutations(prefix, other, result)
        other.append(prefix.pop())


def permutations_in_place(list, len_prefix, result):

    def swap(i, j):
        list[i], list[j] = list[j], list[i]

    if len_prefix == len(list) - 1:
        result.append(tuple(list))
        return

    for i in range(len_prefix, len(list)):
        swap(i, len_prefix)
        permutations_in_place(list, len_prefix+1, result)
        swap(i, len_prefix)


r = list()

# permutations([], [1, 2, 3, 4], r)
permutations_in_place([1, 2, 3, 4], 0, r)

for x in r:
    print(x)

print(len(set(r)), "items")
