DELIMITER //

DROP PROCEDURE IF EXISTS set_random_cities;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_random_cities`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE random_street_id BIGINT;
    
    DECLARE company_cursor CURSOR FOR
        SELECT street_id
        FROM companies;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN company_cursor;
    
    read_loop: LOOP
        FETCH company_cursor INTO random_street_id;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        -- Generate random country_id
        SET random_street_id = FLOOR(RAND() * 1000) + 1;
        
        -- Update cities table with random country_id
        UPDATE companies SET street_id = random_street_id WHERE id = random_street_id;
    END LOOP;
    
    CLOSE company_cursor;
END//

DROP PROCEDURE IF EXISTS set_random_companies;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_random_companies`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE random_country_id BIGINT;
    
    DECLARE cities_cursor CURSOR FOR
        SELECT country_id
        FROM cities;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN cities_cursor;
    
    read_loop: LOOP
        FETCH cities_cursor INTO random_country_id;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        -- Generate random country_id
        SET random_country_id = FLOOR(RAND() * 1000) + 1;
        
        -- Update cities table with random country_id
        UPDATE cities SET country_id = random_country_id WHERE id = random_country_id;
    END LOOP;
    
    CLOSE cities_cursor;
END//

DROP PROCEDURE IF EXISTS set_random_invitations;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_random_invitations`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE random_conversation_id, random_operator_id BIGINT;
    DECLARE existing_count INT;
    
    DECLARE conversation_cursor CURSOR FOR
        SELECT id
        FROM conversations;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN conversation_cursor;
    
    read_loop: LOOP
        FETCH conversation_cursor INTO random_conversation_id;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        -- Check if an invitation already exists for the conversation
        SELECT COUNT(*) INTO existing_count FROM invitations WHERE conversation_id = random_conversation_id;
        
        -- If no existing invitation, insert random invitation
        IF existing_count = 0 THEN
            -- Generate random operator_id and conversation_id
            SET random_operator_id = FLOOR(RAND() * 1000) + 1;
            SET random_conversation_id = FLOOR(RAND() * 1000) + 1;
            
            -- Insert random invitation
            UPDATE invitations SET conversation_id = random_conversation_id WHERE id = random_conversation_id;
            UPDATE invitations SET invited_operator_id = random_operator_id WHERE id = random_operator_id;
        END IF;
    END LOOP;
    
    CLOSE conversation_cursor;
END//

DROP PROCEDURE IF EXISTS set_random_messages;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_random_messages`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE random_conversation_id, random_receiver_id, random_sender_id BIGINT;
    
    DECLARE conversation_cursor CURSOR FOR
        SELECT id
        FROM conversations;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN conversation_cursor;
    
    read_loop: LOOP
        FETCH conversation_cursor INTO random_conversation_id;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        -- Generate random receiver_id, sender_id, and conversation_id
        SET random_receiver_id = FLOOR(RAND() * 1000) + 1;
        SET random_sender_id = FLOOR(RAND() * 1000) + 1;
        SET random_conversation_id = FLOOR(RAND() * 1000) + 1;
        
        -- Update random message
        UPDATE messages SET conversation_id = random_conversation_id WHERE id = random_conversation_id;
        UPDATE messages SET receiver_id = random_receiver_id WHERE id = random_receiver_id;
        UPDATE messages SET sender_id = random_sender_id WHERE id = random_sender_id;
    END LOOP;
    
    CLOSE conversation_cursor;
END//

DROP PROCEDURE IF EXISTS set_random_people;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_random_people`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE random_company_id BIGINT;
    
    DECLARE operator_cursor CURSOR FOR
        SELECT id
        FROM people
        WHERE person_type = 'Operator';
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN operator_cursor;
    
    read_loop: LOOP
        FETCH operator_cursor INTO random_company_id;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        -- Generate random company_id
        SET random_company_id = FLOOR(RAND() * 1000) + 1;
        
        -- Update people table with random company_id for Operators
        UPDATE people SET company_id = random_company_id WHERE id = random_company_id;
    END LOOP;
    
    CLOSE operator_cursor;
END//

DROP PROCEDURE IF EXISTS set_random_streets;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_random_streets`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE random_city_id BIGINT;
    
    DECLARE street_cursor CURSOR FOR
        SELECT id
        FROM streets;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN street_cursor;
    
    read_loop: LOOP
        FETCH street_cursor INTO random_city_id;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        -- Generate random city_id
        SET random_city_id = FLOOR(RAND() * 1000) + 1;
        
        -- Update streets table with random city_id
        UPDATE streets SET city_id = random_city_id WHERE id = random_city_id;
    END LOOP;
    
    CLOSE street_cursor;
END//

DELIMITER ;
