import unittest
import day17


class MyTestCase(unittest.TestCase):
    def test_sample(self):
        jet_pattern = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        chamber = day17.Chamber(jet_pattern)
        while chamber.num_rocks_landed < 2022:
            chamber.tick()
        print(chamber.num_rocks_landed)
        self.assertTrue(True)
        # self.assertEqual(True, False)  # add assertion here


if __name__ == '__main__':
    unittest.main()
