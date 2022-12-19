import unittest
import day17


class MyTestCase(unittest.TestCase):
    def test_part_one_sample(self):
        jet_pattern = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        chamber = day17.Chamber(jet_pattern)
        while chamber.num_rocks_landed < 2022:
            chamber.tick()
        self.assertEqual(3068, chamber.num_rocks_landed)


if __name__ == '__main__':
    unittest.main()
