
def three_sum_sorted(nums):

    result = []

    nums.sort()

    for i in range(len(nums)):

        if i > 0 and nums[i] == nums[i-1]:
            continue

        j = i + 1
        k = len(nums) - 1

        while j < k:

            if nums[i] + nums[j] + nums[k] > 0:
                k -= 1

            elif nums[i] + nums[j] + nums[k] < 0:
                j += 1

            else:
                result.append([nums[i], nums[j], nums[k]])
                while j < k and nums[k] == nums[k-1]:
                    k -= 1
                while j < k and nums[j] == nums[j+1]:
                    j += 1
                k -= 1
                j += 1

    return result


def three_sum_with_set(nums):

    result = set()

    for i in range(len(nums)):
        two_add(nums, i, result)

    return result


# Adds triplets in result, where nums[i] is included, looks from nums[i+1]
def two_add(nums, i, result):

    value = nums[i]
    found = set()

    for i in range(i + 1, len(nums)):

        current = nums[i]
        required = -(value + current)
        if required in found:
            triplet = [value, required, current]
            result.add(tuple(triplet))
        found.add(current)


print(three_sum_sorted([-4, 2, 2, 2, 2]))

