from random import randint


# does 1 quick-sort partition
def partition1(arr, left, right, pivot_idx):

    # move pivot to right
    arr[right], arr[pivot_idx] = arr[pivot_idx], arr[right]
    pivot = arr[right]
    swap_idx = left  # items to the left of this index are smaller than pivot

    # move small items to the left
    for i in range(left, right):
        if arr[i] < pivot:
            arr[i], arr[swap_idx] = arr[swap_idx], arr[i]
            swap_idx += 1

    # now arr[swap_idx] >= pivot, so move swap the pivot there
    arr[right], arr[swap_idx] = arr[swap_idx], arr[right]

    return swap_idx


def quick_sort(array):
    quick_sort_helper(array, 0, len(array)-1)


def quick_sort_helper(arr, left, right):

    swap_idx = partition1(arr, left, right, randint(left, right))

    if left < swap_idx - 1:
        quick_sort_helper(arr, left, swap_idx - 1)

    if right > swap_idx + 1:
        quick_sort_helper(arr, swap_idx + 1, right)





array = [randint(100, 999) for _ in range(15)]
print(array)
quick_sort(array)
print(array)
print("Correct?", array == sorted(array))

# idx = partition1(array, 0, len(array)-1, 0)
# print(f"Partition at index {idx}")
# print(array)
