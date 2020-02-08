

# Sorting a list with 3 unique numbers (1, 2, 3)

def sort3(list):

    i = i1 = 0              # nums before i1 are 1's
    j = j3 = len(list) - 1  # nums after j3 are 3's

    def swap(a, b):
        list[a], list[b] = list[b], list[a]

    while i < j:

        if list[i] == 1:
            if i1 == i:
                i += 1
                i1 = i
            else:
                swap(i, i1)
                i1 += 1
        elif list[j] == 3:
            if j3 == j:
                j -= 1
                j3 = j
            else:
                swap(j, j3)
                j3 -= 1
        elif list[i] == 3 or list[j] == 1:
            swap(i, j)
        else:  # list[i] == list[j] == 2
            i += 1
            j -= 1


a = [3, 1, 2, 1, 2, 2, 1, 2, 1, 1, 2, 3, 2, 1]
sort3(a)

print(a)



