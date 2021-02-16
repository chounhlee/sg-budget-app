import React from 'react'
import AllocateModal from '../components/AllocateModal'

function AllocatePage() {
    return (
        <div id="allocate_page" className="App-page">
                <h2 id="allocateHeader">
                    Allocate Funds
                    <AllocateModal></AllocateModal>
                </h2>
            </div>
    )
}

export default AllocatePage