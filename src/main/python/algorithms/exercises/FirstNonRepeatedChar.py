from collections import OrderedDict

def first_non_repeated_char(s):
    single = OrderedDict()
    duplicated = set()
    for c in s:
        if c not in duplicated:
            if c in single:
                del single[c]
                duplicated.add(c)
            else:
                single[c] = 1  # use dict as set
    return next(iter(single))

print(first_non_repeated_char("aaabccc"))