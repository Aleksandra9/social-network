CREATE INDEX if not exists user_info_first_name_last_name_index ON network.user_info
    USING btree (first_name text_pattern_ops, second_name text_pattern_ops);