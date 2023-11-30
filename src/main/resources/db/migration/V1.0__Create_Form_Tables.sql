-- Drop tables if they exist
DROP TABLE IF EXISTS public."headerDetails" CASCADE;
DROP TABLE IF EXISTS public."header2attribute" CASCADE;
DROP TABLE IF EXISTS public."formAttribute" CASCADE;
DROP TABLE IF EXISTS public."header2user" CASCADE;
DROP TABLE IF EXISTS public."formHeader" CASCADE;
DROP TABLE IF EXISTS public."businessContext" CASCADE;
DROP TABLE IF EXISTS public."business2header" CASCADE;

-- Create public.formHeader table
CREATE TABLE public."formHeader" (
                                  "id" bigserial not null primary key,
                                  "name" varchar(255),
                                  "isActive" bool,
                                  "type" varchar(255),
                                  "createdAt" timestamptz NOT NULL DEFAULT now(),
                                  "modifiedAt" timestamptz NOT NULL DEFAULT now()
);

-- Create public.businessContext table
CREATE TABLE public."businessContext" (
                                     "id" bigserial not null primary key,
                                     "name" varchar(255),
                                     "createdAt" timestamptz NOT NULL DEFAULT now(),
                                     "modifiedAt" timestamptz NOT NULL DEFAULT now()
);

-- Create public.business2header table
CREATE TABLE public."business2header" (
                                      "id" bigserial not null primary key,
                                      "headerId" int8 NOT null references public."formHeader",
                                      "businessId" int8 NOT null references public."businessContext",
                                      "isActive" bool NOT NULL DEFAULT true,
                                      "createdAt" timestamptz NOT NULL DEFAULT now(),
                                      "modifiedAt" timestamptz NOT NULL DEFAULT now()
);

-- Create public.header2user table
CREATE TABLE public."header2user" (
                                   "id" bigserial not null primary key,
                                   "headerId" int8 NOT null references public."formHeader",
                                   "userId" varchar(255),
                                   "realm" varchar(255),
                                   "action" varchar(255),
                                   "createdAt" timestamptz NOT NULL DEFAULT now(),
                                   "modifiedAt" timestamptz NOT NULL DEFAULT now()
);

-- Create public.formAttribute table
CREATE TABLE public."formAttribute" (
                                     "id" bigserial not null primary key,
                                     "name" varchar(255),
                                     "type" varchar(255),
                                     "defaultValue" varchar(255),
                                     "values" text,
                                     "parentId" int8,
                                     "createdAt" timestamptz NOT NULL DEFAULT now(),
                                     "modifiedAt" timestamptz NOT NULL DEFAULT now()
);

-- Create public.header2attribute table
CREATE TABLE public."header2attribute" (
                                        "id" bigserial not null primary key,
                                        "headerId" int8 NOT null references public."formHeader",
                                        "attributeId" int8 NOT null references public."formAttribute",
                                        "isActive" bool NOT NULL DEFAULT true,
                                        "isRequired" bool,
                                        "regexValid" varchar(255),
                                        "order" int8 DEFAULT 1,
                                        "createdAt" timestamptz NOT NULL DEFAULT now(),
                                        "modifiedAt" timestamptz NOT NULL DEFAULT now()
);

-- Create public.headerDetails table
CREATE TABLE public."headerDetails" (
                                     "id" bigserial not null primary key,
                                     "header2userId" int8 NOT null references public."header2user",
                                     "header2attributeId" int8 NOT null references public."header2attribute",
                                     "value" varchar(255),
                                     "createdAt" timestamptz NOT NULL DEFAULT now(),
                                     "modifiedAt" timestamptz NOT NULL DEFAULT now()
);