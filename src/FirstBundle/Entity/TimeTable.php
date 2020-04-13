<?php

namespace FirstBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * TimeTable
 *
 * @ORM\Table(name="time_table")
 * @ORM\Entity
 */
class TimeTable
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="activity1", type="string", length=255, nullable=true)
     */
    private $activity1 = 'NULL';

    /**
     * @var string
     *
     * @ORM\Column(name="activity2", type="string", length=255, nullable=true)
     */
    private $activity2 = 'NULL';

    /**
     * @var string
     *
     * @ORM\Column(name="activity3", type="string", length=255, nullable=true)
     */
    private $activity3 = 'NULL';

    /**
     * @var string
     *
     * @ORM\Column(name="activity4", type="string", length=255, nullable=true)
     */
    private $activity4 = 'NULL';

    /**
     * @var string
     *
     * @ORM\Column(name="class", type="string", length=20, nullable=false)
     */
    private $class;

    /**
     * @var string
     *
     * @ORM\Column(name="date", type="string", length=255, nullable=false)
     */
    private $date;

    /**
     * @var integer
     *
     * @ORM\Column(name="jour", type="integer", nullable=true)
     */
    private $jour = 'NULL';


}

