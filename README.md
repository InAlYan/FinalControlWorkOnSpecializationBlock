# FinalControlWorkOnSpecializationBlock

## Работа с Linux
### Задание 1
    Создать два текстовых файла: "Pets"(Домашние животные) и "Pack animals"(вьючные животные), используя команду `cat` в терминале Linux. В первом файле перечислить собак, кошек и хомяков. Во втором — лошадей, верблюдов и ослов.
   - Объединить содержимое этих двух файлов в один и просмотреть его содержимое.
   - Переименовать получившийся файл в "Human Friends".

    Пример конечного вывода после команды “ls” :
    Desktop Documents Downloads  HumanFriends.txt  Music  PackAnimals.txt  Pets.txt  Pictures  Videos

***Решение:***
---
mkdir final_control_work_specialization \
cat > pets \
1 Fido Dog 2020--01-01 Sit,Stay,Fetch \
2 Whisker Cat 2019-05-15 Sit,Pounce \
3 Hammy Hamster 2021-03-10 Roll,Hide \
4 Buddy Dog 2018-12-10 Sit,Paw,Bark \
5 Smudge Cat 2020-02-20 Sit,Pounce,Scratch \
6 Peanut Hamster 2021-08-01 Roll,Spin \
7 Bella Dog 2019-11-11 Sit,Stay,Roll \
8 Oliver Cat 2020-06-30 Meow,Scratch,Jump \

cat > pack_animals

1 Thunder Horse 2015-07-21 Trot,Canter,Gallop \
2 Sandy Camel 2016-11-03 Walk,Carry,Load \
3 Eyeore Donkey 2017-09-18 Walk,Carry,Load,Bray \
4 Storm Horse 2014-05-05 Trot,Canter \
5 Dune Camel 2018-12-12 Walk,Sit \
6 Burro Donkey 2019-01-23 Walk,Bray,Kick \
7 Blaze Horse 2016-02-29 Trot,Jump,Gallop \
8 Sahara Camel 2015-08-14 Walk,Run \

cat pets pack_animals > pets_and_pack_animals\
cat pets_and_pack_animals

![результаты объединения в командной строке](./img/cat%20pets_and_pack_animals.png "результаты в командной строке")
Рис.1: результаты объединения в командной строке

mv pets_and_pack_animals human_friends \
ls

![результаты переименования в командной строке](./img/ls.png "результаты в командной строке")

Рис.2: результаты переименования в командной строке

### Задание 2
    Создать директорию, переместить файл туда.

***Решение***
---
mkdir new_directory \
mv human_friends new_directory

![результаты перемещения в командной строке](./img/mv%20file.png "результаты перемещения в командной строке")
Рис.3: результаты перемещения в командной строке
