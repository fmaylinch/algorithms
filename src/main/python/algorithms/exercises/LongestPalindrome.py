
def take_palindrome(s, i, j):
    while i > 0 and j < len(s)-1 and s[i-1] == s[j+1]:
        i -= 1
        j += 1
    r = s[i:j+1]
    print("Found " + r)
    return [i, j+1]


def longest_palindrome(s):
    """
    :type s: str
    :rtype: str
    """

    if len(s) <= 1 or (len(s) == 2 and s[0] == s[1]):
        return s

    longest = [0, 1]

    for i in range(1, len(s)-1):

        if s[i] == s[i+1]:
            p = take_palindrome(s, i, i+1)
            longest = max(p, longest, key=lambda x: x[1] - x[0])

        if s[i-1] == s[i+1]:
            p = take_palindrome(s, i-1, i+1)
            longest = max(p, longest, key=lambda x: x[1] - x[0])

    return s[longest[0]:longest[1]]


print(longest_palindrome("z234a5abbba54a32z"))
