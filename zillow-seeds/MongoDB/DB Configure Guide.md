# MongoDB Configure Guide

### Collection: Item
```bash
db.item.insertMany(
[{
  "_id": {
    "$oid": "654e5a0e5522eb326f02d68d"
  },
  "title": "24300 Malibu Rd, Beverly Hills, CA 90265",
  "sales": 300,
  "recommendation": false,
  "weight": 0,
  "city": "Beverly Hills",
  "price": 13000,
  "info": {
    "orientation": "North",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "years": "2000",
    "area": "38000",
    "baths": "3",
    "beds": "3"
  },
  "imgs": [
    "group1/M00/00/01/CgAEDGY3XoWAOHQjAAOrFFgZlxI45.jpeg",
    "group1/M00/00/00/CgAEDGVd4TWAGcADAAapoldvuJY801.png",
    "group1/M00/00/00/CgAEDGVd4SOAOm7GAAccr9iz7Ew359.png"
  ],
  "_class": "com.example.entity.Item",
  "buytime": {
    "$date": "2023-11-05T16:00:00.000Z"
  },
  "isRented": true,
  "rentType": "Whole Rental",
  "houseType": "60m² "
},
{
  "_id": {
    "$oid": "655052c42f3787caf19f01d3"
  },
  "title": "1028 Ridgedale Dr, Los Angeles, CA 90210",
  "sales": 300,
  "recommendation": false,
  "weight": 6,
  "city": "Cape Coral",
  "price": 3000,
  "rentType": "Whole Rental",
  "info": {
    "orientation": "North",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "years": "2000",
    "area": "2100",
    "baths": "3",
    "beds": "4"
  },
  "imgs": [
    "group1/M00/00/01/CgAEDGY3XIKANjS_AAUpWqC7DMo72.webp",
    "group1/M00/00/01/CgAEDGY3XISARM_vAAK8LkSTM6g12.webp",
    "group1/M00/00/01/CgAEDGY3XIaAP7YpAAF9_I-wWs851.webp"
  ],
  "_class": "com.example.entity.Item",
  "buytime": {
    "$date": "2021-12-06T16:00:00.000Z"
  },
  "isRented": false,
  "houseType": "60m² "
},
{
  "_id": {
    "$oid": "655dbbc11d461d991c2607f4"
  },
  "title": "4122 SW 25th Pl, Cape Coral, FL 33914",
  "sales": 300,
  "recommendation": true,
  "weight": 9,
  "city": "Cape Coral",
  "price": 3100,
  "rentType": "Whole Rental",
  "info": {
    "orientation": "North",
    "level": "1",
    "style": "A",
    "type": "Single Family Residence",
    "years": "2000",
    "area": "1200",
    "baths": "3",
    "beds": "3"
  },
  "imgs": [
    "group1/M00/00/01/CgAEDGY3VhKABXwkAASxPO8tq6s062.jpg",
    "group1/M00/00/01/CgAEDGY3Vh2ALaOjAAJ1vQZ08s8350.jpg",
    "group1/M00/00/01/CgAEDGY3ViKATBqgAAN9-ubkMlM845.jpg"
  ],
  "_class": "com.example.entity.Item",
  "buytime": {
    "$date": "2021-12-09T16:00:00.000Z"
  },
  "isRented": false,
  "houseType": "60m² "
},
{
  "_id": {
    "$oid": "655dd33a1d461d991c2607fd"
  },
  "title": "1411 SE 5th Pl, Cape Coral, FL 33990",
  "sales": 300,
  "recommendation": true,
  "weight": 8,
  "city": "Cape Coral",
  "price": 1100,
  "rentType": "Whole Rental",
  "info": {
    "orientation": "North",
    "level": "1",
    "style": "A",
    "type": "Single Family Residence",
    "years": "2000",
    "area": "1600",
    "baths": "3",
    "beds": "5"
  },
  "imgs": [
    "group1/M00/00/01/CgAEDGY3V5SAcp5DAAROLLtq1To54.jpeg",
    "group1/M00/00/01/CgAEDGY3V5WANET6AAJFpKRfiW044.jpeg",
    "group1/M00/00/01/CgAEDGY3V5mAB9zUAAHwd7RSNzw28.jpeg"
  ],
  "_class": "com.example.entity.Item",
  "buytime": {
    "$date": "2023-12-08T16:00:00.000Z"
  },
  "isRented": true,
  "houseType": "60m² "
},
{
  "_id": {
    "$oid": "6565b5a8628228705c6f0664"
  },
  "title": "4231 W Lawther Dr, Dallas, TX 75214",
  "sales": 300,
  "recommendation": true,
  "weight": 2,
  "city": "Dallas",
  "price": 1397,
  "rentType": "Whole Rental",
  "info": {
    "orientation": "North",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "years": "2000",
    "area": "4500",
    "baths": "8",
    "beds": "8"
  },
  "imgs": [
    "group1/M00/00/00/CgAEDGV1lK2ACV1vAAPUwoUP7kc26.webp",
    "group1/M00/00/00/CgAEDGV1lXiAFdhrAAHn5Dvq6A828.webp",
    "group1/M00/00/01/CgAEDGY2FNSAVLoQAAFcAjUXV6A41.webp"
  ],
  "_class": "com.example.entity.Item",
  "buytime": {
    "$date": "2024-12-09T16:00:00.000Z"
  },
  "isRented": true,
  "houseType": "60m² "
},
{
  "_id": {
    "$oid": "656accdb275e8bf9d0d9794a"
  },
  "title": "5116 Terry St, Dallas, TX 75223",
  "sales": 300,
  "recommendation": false,
  "weight": 2,
  "city": "Dallas",
  "price": 7000,
  "rentType": "Whole Rental",
  "info": {
    "orientation": "North",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "years": "2000",
    "area": "5000",
    "baths": "6",
    "beds": "6"
  },
  "imgs": [
    "group1/M00/00/01/CgAEDGY2M_2ABcVCAAFgNtKXrJE67.webp",
    "group1/M00/00/01/CgAEDGY2MQKAXhJvAAFYBsS72to42.webp",
    "group1/M00/00/01/CgAEDGY2SGyANxPDAAJFIMoBnBg632.png"
  ],
  "_class": "com.example.entity.Item",
  "buytime": {
    "$date": "2023-12-08T16:00:00.000Z"
  },
  "isRented": true,
  "houseType": "60m² "
},
{
  "_id": {
    "$oid": "656acce5275e8bf9d0d9794b"
  },
  "title": "2606 Fordham Rd, Dallas, TX 75216",
  "sales": 300,
  "recommendation": false,
  "weight": 3,
  "city": "Dallas",
  "price": 1300,
  "rentType": "Whole Rental",
  "info": {
    "orientation": "North",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "years": "2000",
    "area": "4000",
    "baths": "4",
    "beds": "4"
  },
  "imgs": [
    "group1/M00/00/01/CgAEDGY2GM-AUOhIAANdwAUuzyg91.webp",
    "group1/M00/00/01/CgAEDGY2GNKALqKJAAGNUs1mQUA11.webp",
    "group1/M00/00/01/CgAEDGY2GNWAXD0IAANdwAUuzyg07.webp"
  ],
  "_class": "com.example.entity.Item",
  "buytime": {
    "$date": "2023-12-08T16:00:00.000Z"
  },
  "isRented": false,
  "houseType": "60m² "
},
{
  "_id": {
    "$oid": "656acf10275e8bf9d0d9794c"
  },
  "title": "5735 Riverside Dr, Cape Coral, FL 33904",
  "sales": 300,
  "recommendation": true,
  "weight": 9,
  "city": "Cape Coral",
  "price": 300,
  "rentType": "Whole Rental",
  "info": {
    "orientation": "North",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "years": "2000",
    "area": "1400",
    "baths": "4",
    "beds": "4"
  },
  "imgs": [
    "group1/M00/00/00/CgAEDGV1nPqALnijAATfyJqeD94375.jpg",
    "group1/M00/00/00/CgAEDGV1nRGAekViAAa2JvVVL2E744.jpg",
    "group1/M00/00/00/CgAEDGV1nPqALnijAATfyJqeD94375.jpg"
  ],
  "_class": "com.example.entity.Item",
  "buytime": {
    "$date": "2023-12-09T16:00:00.000Z"
  },
  "isRented": false,
  "houseType": "60m² "
},
{
  "_id": {
    "$oid": "6575983544d016a49482012d"
  },
  "title": "8750 Park Ln APT 159, Dallas, TX 75231",
  "sales": 300,
  "recommendation": true,
  "weight": 2,
  "city": "Dallas",
  "price": 3977,
  "rentType": "Whole Rental",
  "info": {
    "orientation": "North",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "years": "2000",
    "area": "3200",
    "baths": "3",
    "beds": "4"
  },
  "imgs": [
    "group1/M00/00/00/CgAEDGV1mB2APFKEAAJhGrB2H-s15.webp",
    "group1/M00/00/00/CgAEDGV1lXiAFdhrAAHn5Dvq6A828.webp",
    "group1/M00/00/00/CgAEDGVd4SmAcsuYABIsJ9aLf_s873.png"
  ],
  "_class": "com.example.entity.Item",
  "buytime": {
    "$date": "2023-12-08T16:00:00.000Z"
  },
  "isRented": true,
  "houseType": "60m² "
},
{
  "_id": {
    "$oid": "6577233344d016a49482015b"
  },
  "title": "5534 Northmoor Dr, Dallas, TX 75230",
  "sales": 300,
  "recommendation": true,
  "weight": 2,
  "city": "Dallas",
  "price": 9080,
  "rentType": "Whole Rental",
  "info": {
    "orientation": "North",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "years": "2023",
    "area": "4340",
    "baths": "4",
    "beds": "6"
  },
  "imgs": [
    "group1/M00/00/00/CgAEDGV3It6AGUvyAAUjGu86prg712.jpg",
    "group1/M00/00/00/CgAEDGV3IuaAaiAuAAUjGu86prg750.jpg",
    "group1/M00/00/00/CgAEDGV3IySAbNh-AALOaglGwR0576.jpg"
  ],
  "_class": "com.example.entity.Item",
  "buytime": {
    "$date": "2023-12-09T16:00:00.000Z"
  },
  "isRented": true,
  "houseType": "60m² "
},
{
  "_id": {
    "$oid": "6577250644d016a49482015c"
  },
  "title": "4731 Wildwood Rd, Dallas, TX 75209",
  "sales": 300,
  "recommendation": false,
  "weight": 1,
  "city": "Dallas",
  "price": 16000,
  "rentType": "Whole Rental",
  "info": {
    "orientation": "North",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "years": "2000",
    "area": "6600",
    "baths": "8",
    "beds": "8"
  },
  "imgs": [
    "group1/M00/00/00/CgAEDGV3JJCAG4PZAAUWDugQUik726.jpg",
    "ggroup1/M00/00/00/CgAEDGV3JJ2AYJ3jAAUWDugQUik934.jpg",
    "group1/M00/00/00/CgAEDGV3JPuABxq3AAQzBs6rMfg054.jpg"
  ],
  "_class": "com.example.entity.Item",
  "buytime": {
    "$date": "2023-12-09T16:00:00.000Z"
  },
  "isRented": false,
  "houseType": "60m² "
},
{
  "_id": {
    "$oid": "65772a9244d016a49482015d"
  },
  "title": "9312 Kendall Ln, North Richland Hills, TX 76182",
  "sales": 300,
  "recommendation": true,
  "weight": 2,
  "city": "Dallas",
  "price": 6580,
  "rentType": "Whole Rental",
  "info": {
    "orientation": "North",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "years": "2023",
    "area": "3400",
    "baths": "3",
    "beds": "4"
  },
  "imgs": [
    "group1/M00/00/00/CgAEDGV3KTWALT7SAAMpNjEUQZ8977.jpg",
    "group1/M00/00/00/CgAEDGV3KlmAXw7kAAGwYkNCUlU038.jpg",
    "group1/M00/00/00/CgAEDGV3KnmAahs2AAEmXhzMGCw850.jpg"
  ],
  "_class": "com.example.entity.Item",
  "buytime": {
    "$date": "2023-12-09T16:00:00.000Z"
  },
  "isRented": false,
  "houseType": "60m² "
},
{
  "_id": {
    "$oid": "6636d88dee93484a7b665489"
  },
  "title": " 1859 Bel Air Rd, Beverly Hills, CA 90077",
  "sales": 300,
  "recommendation": true,
  "weight": 0,
  "price": 230000,
  "city": "Beverly Hills",
  "rentType": "Room Rental",
  "houseType": "",
  "info": {
    "area": "60431",
    "orientation": "North",
    "baths": "12",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "beds": "9",
    "years": "2023"
  },
  "imgs": [
    "group1/M00/00/01/CgAEDGY22EWAPlRyAAKEd6XGeJc824.jpg",
    "group1/M00/00/01/CgAEDGY22ECAO1B-AAFpQobw_BE70.jpeg",
    "group1/M00/00/01/CgAEDGY22EGAFghTAAGJ8vLaQrA02.jpeg"
  ],
  "buytime": {
    "$date": "2024-05-04T16:00:00.000Z"
  },
  "isRented": false,
  "_class": "com.example.entity.Item"
},
{
  "_id": {
    "$oid": "663765c8a1aeb96679a51ebd"
  },
  "title": "2002 Loma Vista Dr, Beverly Hills, CA 90210",
  "sales": 300,
  "recommendation": true,
  "weight": 0,
  "price": 41000,
  "city": "Beverly Hills",
  "rentType": "Whole Rental",
  "houseType": "",
  "info": {
    "area": "31000",
    "orientation": "North",
    "baths": "9",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "beds": "5",
    "years": "2022"
  },
  "imgs": [
    "group1/M00/00/01/CgAEDGY3Zy-AINwPAAec3yjG6Gs441.jpg",
    "group1/M00/00/01/CgAEDGY3ZZKADVotABv2R2xYQ3I564.png",
    "group1/M00/00/01/CgAEDGY3ZxuADwyyAAJEo6yyEvo18.jpeg"
  ],
  "buytime": {
    "$date": "2024-05-05T10:56:08.549Z"
  },
  "isRented": false,
  "_class": "com.example.entity.Item"
},
{
  "_id": {
    "$oid": "66382d2c89763423465d81cf"
  },
  "title": "31555 Pacific Coast Hwy, Beverly Hills, CA 90265",
  "sales": 300,
  "recommendation": false,
  "weight": 0,
  "price": 19500,
  "city": "Beverly Hills",
  "rentType": "Room Rental",
  "houseType": "",
  "info": {
    "area": "310660",
    "orientation": "North",
    "baths": "13",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "beds": "7",
    "years": "2023"
  },
  "imgs": [
    "group1/M00/00/01/CgAEDGY4LSWAWKUFAALrwGof_WM26.jpeg",
    "group1/M00/00/01/CgAEDGY4LSaAWWJ5AAOvsT4Fc-s03.jpeg",
    "group1/M00/00/01/CgAEDGY4LSqAMePEAAGPyCZhHe498.jpeg"
  ],
  "buytime": {
    "$date": "2024-05-05T16:00:00.000Z"
  },
  "isRented": false,
  "_class": "com.example.entity.Item"
},
{
  "_id": {
    "$oid": "6638330189763423465d81d0"
  },
  "title": "9016 Thrasher Ave, Los Angeles, CA 90069",
  "sales": 300,
  "recommendation": false,
  "weight": 0,
  "price": 12000,
  "city": "Beverly Hills",
  "rentType": "Whole Rental",
  "houseType": "",
  "info": {
    "area": "43560",
    "orientation": "West",
    "baths": "7.5",
    "level": "1",
    "style": "Fully Furnished",
    "type": "Single Family Residence",
    "beds": "5",
    "years": "2022"
  },
  "imgs": [
    "group1/M00/00/02/CgAEDGY4Mx-AZfYMAIQ7Nt_0abM351.jpg",
    "group1/M00/00/01/CgAEDGY4MnKAbUimAJeY7E15pzU717.jpg",
    "group1/M00/00/02/CgAEDGY4NnSAUS8QALSTIwhSd0c967.jpg"
  ],
  "buytime": {
    "$date": "2024-05-06T01:31:45.927Z"
  },
  "isRented": false,
  "_class": "com.example.entity.Item"
}]
]);
```

### Collection: Comment
```bash
db.comment.insertMany(
[{
  "_id": {
    "$oid": "6576e86973e08d72f3e639db"
  },
  "username": "1",
  "comment": "SSS",
  "star": 3,
  "itemId": "655dd33a1d461d991c2607fd",
  "_class": "com.example.entity.Comment"
},
{
  "_id": {
    "$oid": "6576e94873e08d72f3e639dc"
  },
  "username": "1",
  "comment": "sss",
  "star": 3,
  "itemId": "655dd33a1d461d991c2607fd",
  "_class": "com.example.entity.Comment"
},
{
  "_id": {
    "$oid": "6576e99c73e08d72f3e639dd"
  },
  "username": "1",
  "comment": "qwedqw",
  "star": 3,
  "itemId": "655dd33a1d461d991c2607fd",
  "_class": "com.example.entity.Comment"
},
{
  "_id": {
    "$oid": "65772d6773e08d72f3e639de"
  },
  "username": "1",
  "comment": "Shit hole!!! Totally!!!",
  "star": 3,
  "itemId": "655dd33a1d461d991c2607fd",
  "_class": "com.example.entity.Comment"
},
{
  "_id": {
    "$oid": "6577cc9d9c51aa5f8d08e100"
  },
  "username": "1",
  "comment": "e42424",
  "star": 3,
  "itemId": "6575983544d016a49482012d",
  "_class": "com.example.entity.Comment"
},
{
  "_id": {
    "$oid": "6577ee4046022439b6267742"
  },
  "username": "1",
  "comment": "111111111112",
  "star": 3,
  "itemId": "655dd33a1d461d991c2607fd",
  "_class": "com.example.entity.Comment"
},
{
  "_id": {
    "$oid": "6632552702fa0534d72a3357"
  },
  "username": "1",
  "comment": "s",
  "star": 3,
  "itemId": "655dd33a1d461d991c2607fd",
  "_class": "com.example.entity.Comment"
},
{
  "_id": {
    "$oid": "6632630e33755454084a0e2a"
  },
  "username": "1",
  "comment": "s",
  "star": 3,
  "itemId": "655dd33a1d461d991c2607fd",
  "_class": "com.example.entity.Comment"
}]
)
```

### Collection: Banner
```bash
db.banner.insertMany(
[{
  "_id": {
    "$oid": "654e5b405522eb326f02d693"
  },
  "url": "group1/M00/00/00/CgAEDGVd4TWAGcADAAapoldvuJY801.png",
  "createTime": {
    "$date": "2023-11-10T16:33:04.267Z"
  }
},
{
  "_id": {
    "$oid": "654e5b405522eb326f02d695"
  },
  "url": "group1/M00/00/00/CgAEDGVd4SmAcsuYABIsJ9aLf_s873.png",
  "createTime": {
    "$date": "2023-11-10T16:33:04.295Z"
  }
},
{
  "_id": {
    "$oid": "654e5b405522eb326f02d696"
  },
  "url": "group1/M00/00/00/CgAEDGVd4TGAaUGTABv2R2xYQ3I511.png",
  "createTime": {
    "$date": "2023-11-10T16:33:04.296Z"
  }
},
{
  "_id": {
    "$oid": "654e5b405522eb326f02d697"
  },
  "url": "group1/M00/00/00/CgAEDGVd4RWAKqoKAAJFIMoBnBg646.png",
  "createTime": {
    "$date": "2023-11-10T16:33:04.298Z"
  }
}]
)
```
Collection: User
```bash
db.user.insertMany(
[{
  "_id": {
    "$oid": "656994e40d9bdb0801830825"
  },
  "username": "palpatine",
  "password": "1",
  "phone": "19124380336",
  "_class": "com.example.entity.User"
},
{
  "_id": {
    "$oid": "6569a6ab668974488c1f6568"
  },
  "username": "1",
  "password": "1",
  "_class": "com.example.entity.User"
},
{
  "_id": {
    "$oid": "656adf78753c7f37c9148966"
  },
  "username": "adffd",
  "password": "1",
  "_class": "com.example.entity.User"
},
{
  "_id": {
    "$oid": "656adf78753c7f37c9148967"
  },
  "username": "adffd",
  "password": "1",
  "_class": "com.example.entity.User"
},
{
  "_id": {
    "$oid": "656adf78753c7f37c9148968"
  },
  "username": "adffd",
  "password": "1",
  "_class": "com.example.entity.User"
}]
)
```