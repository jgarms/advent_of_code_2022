import unittest
import day17


class Day17TestCases(unittest.TestCase):
    def test_part_one_sample(self):
        jet_pattern = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        chamber = day17.Chamber(jet_pattern, 2022)
        chamber.run()
        self.assertEqual(3068, chamber.height)


if __name__ == '__main__':
    unittest.main()
