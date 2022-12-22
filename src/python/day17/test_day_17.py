import unittest
import day17


class Day17TestCases(unittest.TestCase):
    def test_part_one_sample(self):
        jet_pattern = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        chamber = day17.Chamber(jet_pattern, 2022)
        chamber.run()
        self.assertEqual(3068, chamber.get_height())

    def test_part_two_sample(self):
        jet_pattern = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        chamber2 = day17.Chamber(jet_pattern, 1000000000000)
        chamber2.run()
        self.assertEqual(1514285714288, chamber2.get_height())


if __name__ == '__main__':
    unittest.main()
