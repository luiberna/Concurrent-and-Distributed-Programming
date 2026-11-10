# ProjetoPCD_26_27_IscoT

Run these commands from the `Project` folder with a JDK and `make` installed.

Recompile the entire project into `out/`:

```sh
make
```

Recompile and run the air conditioning demo:

```sh
make run
```

The default arguments are `localhost 12345 Kitchen`. To change them:

```sh
make run ARGS="localhost 12345 LivingRoom"
```

To run another class after rebuilding:

```sh
make run MAIN=iscot.client.actuators.Light ARGS="localhost 12345 Kitchen"
```

Every build recompiles all `.java` files under `src/`. `make run` starts the
program only if compilation succeeds.
