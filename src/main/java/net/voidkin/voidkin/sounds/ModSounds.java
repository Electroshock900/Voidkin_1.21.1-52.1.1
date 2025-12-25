package net.voidkin.voidkin.sounds;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;
import net.voidkin.voidkin.VoidkinMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, VoidkinMod.MODID);

    public static final RegistryObject<SoundEvent> SOUND_BLOCK_BREAK = registerSoundEvents("sound_block_break");
    public static final RegistryObject<SoundEvent> SOUND_BLOCK_STEP = registerSoundEvents("sound_block_step");
    public static final RegistryObject<SoundEvent> SOUND_BLOCK_FALL = registerSoundEvents("sound_block_fall");
    public static final RegistryObject<SoundEvent> SOUND_BLOCK_PLACE = registerSoundEvents("sound_block_place");
    public static final RegistryObject<SoundEvent> SOUND_BLOCK_HIT = registerSoundEvents("sound_block_hit");

    public static final RegistryObject<SoundEvent> BAR_BRAWL = registerSoundEvents("bar_brawl");
    public static final ResourceKey<JukeboxSong> BAR_BRAWL_KEY = createSong("bar_brawl");

    public static final RegistryObject<SoundEvent> GRAVITY = registerSoundEvents("gravity");
    public static final ResourceKey<JukeboxSong> GRAVITY_KEY = createSong("gravity");

    public static final RegistryObject<SoundEvent> DAISIES = registerSoundEvents("daisies");
    public static final ResourceKey<JukeboxSong> DAISIES_KEY = createSong( "daisies");

    public static final RegistryObject<SoundEvent> UPSIDE_DOWN = registerSoundEvents("upside_down");
    public static final ResourceKey<JukeboxSong> UPSIDE_DOWN_KEY = createSong("upside_down");

    public static final RegistryObject<SoundEvent> LOSER_BABY = registerSoundEvents("loser_baby");
    public static final ResourceKey<JukeboxSong> LOSER_BABY_KEY = createSong("loser_baby");

    public static final RegistryObject<SoundEvent> LOSIN_STREAK = registerSoundEvents("losin_streak");
    public static final ResourceKey<JukeboxSong> LOSIN_STREAK_KEY = createSong("losin_streak");

    public static final RegistryObject<SoundEvent> HUSK_LOVE = registerSoundEvents("love_in_a_bottle");
    public static final ResourceKey<JukeboxSong> HUSK_LOVE_KEY = createSong( "love_in_a_bottle");

    public static final RegistryObject<SoundEvent> ALASTOR = registerSoundEvents("alastor_mashup");
    public static final ResourceKey<JukeboxSong> ALASTOR_KEY = createSong("alastor_mashup");

    public static final RegistryObject<SoundEvent> BRIGHTER = registerSoundEvents("brighter");
    public static final ResourceKey<JukeboxSong> BRIGHTER_KEY = createSong("brighter");

    public static final ForgeSoundType SOUND_BLOCK_SOUNDS = new ForgeSoundType(1f, 1f,
            ModSounds.SOUND_BLOCK_BREAK, ModSounds.SOUND_BLOCK_STEP, ModSounds.SOUND_BLOCK_PLACE,
            ModSounds.SOUND_BLOCK_HIT, ModSounds.SOUND_BLOCK_FALL);


    private static ResourceKey<JukeboxSong> createSong(String name){
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID, name));
    }

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(VoidkinMod.MODID, name)));
    }
    public static void register(IEventBus eventBus){SOUND_EVENTS.register(eventBus);}
    private static Holder.Reference<SoundEvent> registerForHolder(String pName) {
        return registerForHolder(ResourceLocation.withDefaultNamespace(pName));
    }

    private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation pName) {
        return registerForHolder(pName, pName);
    }

    private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation pName, ResourceLocation pLocation) {
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, pName, SoundEvent.createVariableRangeEvent(pLocation));
    }

}
