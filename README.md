# Optimized Fullbright



## How does this mod differ from the others?
Instead of just changing minecraft's gamma value like many other fullbright mods, this mod also completely **disables** minecraft's lighting engine. This reduces the number of lighting calculations the game has to perform, improving performance since there's no reason to compute light levels when they’re always at the maximum.

Because of this, F3 will display every block’s light level as 15, indicating that the mod is working.

![working example](https://cdn.modrinth.com/data/cached_images/cc830668bf1e7ad80eb001b5e067fe196a174b47.png)


##  Performance comparison
With a simulated lag machine designed to cause more light updates:

(**LEFT**) Fullbright Disabled : ~210 fps

‎(**RIGHT**) Fullbright Enabled : ~260fps

<img src="https://cdn.modrinth.com/data/cached_images/929afcfd85d340c829b3c189ba88811b5c18dfb8_0.webp" alt="Alt text" width="50%"><img src="https://cdn.modrinth.com/data/cached_images/98ee3c6744733c62284cc1339aee1b3bafe54c17_0.webp" alt="Alt text" width="50%">

Of course, in normal gameplay the performance gap will be smaller. However, it can provide noticeable improvements in areas with huge amount of block/light updates, e.g. large scale farms that use pistons.

### Compatible renderers:
- Vanilla
- Sodium


By default, fullbright is toggleable by using the key (**G**). It can be changed in the controls setting.

_This mod is purely **visual** and does not affect any other game mechanics._



