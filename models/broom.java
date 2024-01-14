public VoxelShape makeShape(){
	VoxelShape shape = VoxelShapes.empty();
	shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.3125, 0.4375, 0.5625, 0.4375, 0.5625));
	shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, -0.0625, 0.5, 0.6875, 0.375, 0.5));
	shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, -0.0625, 0.5, 0.6875, 0.375, 0.5));
	shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, -0.0625, 0.5, 0.6875, 0.375, 0.5));
	shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, -0.0625, 0.3125, 0.5, 0.375, 0.6875));
	shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, -0.0625, 0.5, 0.6875, 0.375, 0.5));
	shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, -0.0625, 0.5, 0.6875, 0.375, 0.5));
	shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, -0.0625, 0.3125, 0.5, 0.375, 0.6875));
	shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, -0.0625, 0.3125, 0.5, 0.375, 0.6875));
	shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.46875, 1.125, 0.46875, 0.53125, 1.875, 0.53125));
	shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.46875, 0.375, 0.46875, 0.53125, 1.125, 0.53125));

	return shape;
}