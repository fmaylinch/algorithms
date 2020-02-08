import collections


def group_anagrams(words):
    groups = collections.defaultdict(list)
    for word in words:
        key = make_counts_key(word)
        groups[key].append(word)
    return groups.values()


def make_counts_key(word):
    count = [0] * 26
    for c in word:
        count[ord(c) - ord('a')] += 1
    return tuple(count)


def make_sorted_key(s):
    return "".join(sorted(s))


result = group_anagrams(['abc', 'bcd', 'cba', 'cbd', 'efg'])
for r in result:
    print(r)
