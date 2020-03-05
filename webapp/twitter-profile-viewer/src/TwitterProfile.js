import React from 'react';
import './App.css';

export function TwitterProfile({ name, screen_name, profile_image_url_https }) {
    if (name) {
        const fullScreenImage = profile_image_url_https.replace('_normal','');
        return (
            <div>
                <img src={fullScreenImage} alt="profile_pic"/>
                <h1>{name}</h1>
                <h2>{screen_name}</h2>
            </div>
        )
    }
    return <div></div>
}
