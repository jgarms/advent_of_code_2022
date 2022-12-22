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

    jet_index = 0
    shape_index = 0
    total_rocks_to_fall: int

    previous_states = {}
    found_cycle = False

    def __init__(self, jet_pattern_string, total_rocks_to_fall):
        self.jet_pattern = jet_pattern_string
        self.total_rocks_to_fall = total_rocks_to_fall

    def tick(self):
        # Do we need a new rock?
        if self.current_rock is None:
            self.current_rock = ALL_SHAPES[self.shape_index]
            self.rock_pos_x = 2
            self.rock_pos_y = self.height + 3

        jet = self.jet_pattern[self.jet_index]
        # Move right or left
        if jet == "<":
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

        if self.height > 30 and self.current_rock is None and not self.found_cycle:
            cache_key_list = [self.shape_index, self.jet_index]
            for x in range(self.width):
                for y in range(0, 30):
                    test_point = Point(x, self.height - y)
                    if test_point in self.contents:
                        cache_key_list.append(Point(x, y))
            cache_key = tuple(cache_key_list)
            if cache_key in self.previous_states:
                print("found")
                self.found_cycle = True
                (height_per_cycle, num_rocks_per_cycle) = self.previous_states[cache_key]
                print("height_per_cycle: ", height_per_cycle)
                print("num_rocks_per_cycle: ", num_rocks_per_cycle)
            else:
                # print("not found")
                self.previous_states[cache_key] = tuple([self.height, self.num_rocks_landed])

        # increment our indices
        if self.current_rock is None:
            self.shape_index += 1
            if self.shape_index >= len(ALL_SHAPES):
                self.shape_index = 0

        self.jet_index += 1
        if self.jet_index >= len(self.jet_pattern):
            self.jet_index = 0

    def run(self):
        while self.num_rocks_landed < self.total_rocks_to_fall:
            self.tick()

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
    chamber = Chamber(jetstream, 2022)
    chamber.run()
    print(chamber.height)
