# DbLoader

State: raw, before testing

Everything is raw so far, but still you can take a look on idea. Stage of initial testing.
Every suggestion & critics are welcome

Notes:
- it is impossible to select virtual columns in java for Oracle (jdbc query doesn't work despite that's direct Oracle), 
so functionality for checking virtual columns was not implemented; it's required to exclude those columns from mapping

- mapping implemented one-to-one now, that does mean reader gets all columns list and tries to insert the same columns list
in the same order to the dest table (to be refactored)

Usage:

Just pass yaml configuration file path as first argument of Loader main's class with content like follows:

mappings:
    -
        source:
            tableName: table
            schemaName: schema1
            connection:
                url: jdbc.oracle.thin@bla.bla1
                user: me
                pass: secret
                vendor: ORACLE
        destination:
            tableName: table
            schemaName: schema2
            connection:
                url: jdbc.oracle.thin@bla.bla2
                user: me
                pass: secret
                vendor: ORACLE
    -
        source:
            tableName: table2
            schemaName: schema1
            connection:
                url: jdbc.oracle.thin@bla.bla1
                user: me
                pass: secret
                vendor: ORACLE
        destination:
            tableName: table2
            schemaName: schema2
            connection:
                url: jdbc.oracle.thin@bla.bla2
                user: me
                pass: secret
                vendor: ORACLE

The loader will queue all mappings serially but will try to parallelize each table loading as best as he can.

