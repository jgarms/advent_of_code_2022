from itertools import cycle
from sys import stdin
from typing import NamedTuple


class Point(NamedTuple):
    x: int
    y: int


class Shape:
    points: set[Point]
    width: int
    height: int

    def __init__(self, points, width, height):
        super().__init__()
        self.points = points
        self.width = width
        self.height = height

    def get_points_for_origin(self, x, y):
        new_points = set()
        for point in self.points:
            new_points.add(Point(point.x + x, point.y + y))
        return new_points


HORIZONTAL_LINE = Shape([Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0)], 4, 1)
PLUS = Shape([Point(1, 0), Point(0, 1), Point(1, 1), Point(2, 1), Point(1, 2)], 3, 3)
CORNER = Shape([Point(0, 0), Point(1, 0), Point(2, 0), Point(2, 1), Point(2, 2)], 3, 3)
VERTICAL_LINE = Shape([Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3)], 1, 4)
BLOCK = Shape([Point(0, 0), Point(1, 0), Point(0, 1), Point(1, 1)], 2, 2)
ALL_SHAPES = [HORIZONTAL_LINE, PLUS, CORNER, VERTICAL_LINE, BLOCK]


class Chamber:
    width = 7
    height = 0
    rock_pos_x = 0
    rock_pos_y = 0
    contents = set()
    num_rocks_landed = 0

    current_rock = None

    jet_pattern: cycle

    shapes: cycle

    def __init__(self, jet_pattern_string):
        self.jet_pattern = cycle(jet_pattern_string)
        self.shapes = cycle(ALL_SHAPES)

    def tick(self):
        # Do we need a new rock?
        if self.current_rock is None:
            self.current_rock = next(self.shapes)
            self.rock_pos_x = 2
            self.rock_pos_y = self.height + 3

        # Move right or left
        if next(self.jet_pattern) == "<":
            if self.rock_pos_x > 0:
                potential_points = self.current_rock.get_points_for_origin(self.rock_pos_x - 1, self.rock_pos_y)
                if potential_points.isdisjoint(self.contents):
                    self.rock_pos_x -= 1
        else:
            if self.rock_pos_x + self.current_rock.width < self.width:
                potential_points = self.current_rock.get_points_for_origin(self.rock_pos_x + 1, self.rock_pos_y)
                if potential_points.isdisjoint(self.contents):
                    self.rock_pos_x += 1

        # Attempt to move down
        potential_points = self.current_rock.get_points_for_origin(self.rock_pos_x, self.rock_pos_y - 1)
        if self.rock_pos_y == 0 or not potential_points.isdisjoint(self.contents):
            # Collision. Stop here and add the rock's points WHERE THEY ARE, not down one
            new_points = self.current_rock.get_points_for_origin(self.rock_pos_x, self.rock_pos_y)
            self.contents |= new_points
            for point in new_points:
                if point.y >= self.height:
                    self.height = point.y + 1
            self.current_rock = None
            self.num_rocks_landed += 1
        else:
            self.rock_pos_y -= 1

    def print_state(self, potential_points):
        for y in range(self.height + 6, -1, -1):
            for x in range(0, self.width):
                point = Point(x, y)
                if point in potential_points:
                    print('@', end='')
                elif point in self.contents:
                    print('#', end='')
                else:
                    print('.', end='')
            print()
        print()


if __name__ == '__main__':
    jetstream = stdin.read()
    chamber = Chamber(jetstream)
    while chamber.num_rocks_landed < 2022:
        chamber.tick()
    print(chamber.height)
