from enum import Enum
from itertools import cycle
from typing import NamedTuple


class Point(NamedTuple):
    x: int
    y: int


class Shape:
    points: list[Point]

    def __init__(self, points):
        super().__init__()
        self.points = points

    def get_points_for_origin(self, x, y):
        new_points = []
        for point in self.points:
            new_points.append(Point(point.x + x, point.y + y))
        return new_points


class Shapes(Enum):
    horizontal_line = Shape([Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0)])
    plus = Shape([Point(1, 0), Point(0, 1), Point(1, 1), Point(2, 1), Point(1, 2)])
    corner = Shape([Point(0, 0), Point(1, 0), Point(2, 0), Point(2, 1), Point(2, 2)])
    vertical_line = Shape([Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3)])
    block = Shape([Point(0, 0), Point(1, 0), Point(0, 1), Point(1, 1)])


class Chamber:
    width = 7
    height = 0
    rock_pos_x = 0
    rock_pos_y = 0
    contents = {}
    num_rocks_landed = 0

    shapes = cycle([Shapes.horizontal_line, Shapes.plus, Shapes.corner, Shapes.vertical_line, Shapes.block])
    current_rock = None

    jet_pattern: cycle

    def __init__(self, jet_pattern_string):
        self.jet_pattern = cycle(jet_pattern_string)

    def tick(self):
        # Do we need a new rock?
        if self.current_rock is None:
            self.current_rock = next(self.shapes)
            self.rock_pos_x = 2
            self.rock_pos_y = self.height + 3

        # Move right or left
        if next(self.jet_pattern) == "<":
            if self.rock_pos_x > 0:
                self.rock_pos_x -= 1
        else:
            if self.rock_pos_x < self.width - 1:
                self.rock_pos_x += 1

        # Attempt to move down
        potential_points = self.current_rock.get_points_for_origin(self.rock_pos_x, self.rock_pos_y - 1)
        if any(point in self.contents for point in potential_points):
            # Collision. Stop here and add the rock's points
            self.contents.update(potential_points)
            for point in potential_points:
                if point.y > self.height - 1:
                    self.height = point.y + 1
            self.current_rock = None
            self.num_rocks_landed += 1
        else:
            self.rock_pos_y -= 1

