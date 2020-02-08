import collections


def can_construct(note, magazine):

    # counts = {}
    # With this dictionary you don't need the `if c not in counts`
    counts = collections.defaultdict(int)

    for c in magazine:
        # if c not in counts:
        #     counts[c] = 0
        counts[c] += 1

    for c in note:
        # if c not in counts:
        #     return False
        counts[c] -= 1
        if counts[c] < 0:
            return False

    return True


print(can_construct("aa", "ab"))
print(can_construct("ab", "ba"))
print(can_construct("ab", "bca"))
